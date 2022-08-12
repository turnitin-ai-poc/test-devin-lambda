import typing as t

from textwrap import dedent
import json
import pathlib
import re

from jinja2.ext import Extension


COOKIECUTTER_PATH = pathlib.Path(__file__).parent / "cookiecutter.json"


class VariableSubstitutionExtension(Extension):
    DEFAULT_VARIABLE_START_STRING = "__TEMP"
    DEFAULT_VARIABLE_END_STRING = "TEMP__"

    @classmethod
    def load_variable_substitution_regex(cls) -> t.Pattern[str]:
        with open(COOKIECUTTER_PATH, "r") as f:
            cookiecutter_config: dict = json.load(f)

        config = cookiecutter_config.get("_variable_substitution", {})
        start_str = config.get("variable_start_string",
                               cls.DEFAULT_VARIABLE_START_STRING)
        end_str = config.get("variable_end_string",
                             cls.DEFAULT_VARIABLE_END_STRING)
        return re.compile(r"{}([\w\d_]*){}".format(start_str, end_str))

    def __init__(self, environment):
        super().__init__(environment)

        self.var_subs_regex = self.load_variable_substitution_regex()

    def preprocess(
            self, source: str, name: t.Optional[str],
            filename: t.Optional[str] = None
    ) -> str:
        if self.var_subs_regex is None:
            return source

        var_start_str = self.environment.variable_start_string
        var_end_str = self.environment.variable_end_string

        def replace_variable(match: t.Match) -> str:
            return f"{var_start_str} cookiecutter.{match[1]} {var_end_str}"

        return self.var_subs_regex.sub(replace_variable, source)


class OptionSelectionTestExtension(Extension):
    IS_SELECTED_VALUES = ["true", "y", "yes", "1"]
    IS_NOT_SELECTED_VALUES = ["false", "n", "no", "0"]
    ACCEPTED_VALUES = IS_SELECTED_VALUES + IS_NOT_SELECTED_VALUES

    def __init__(self, environment):
        super().__init__(environment)

        self.environment.tests["selected_option"] = self.test_selected_option

    def test_selected_option(self, raw_value: t.Union[str, bool]):
        if isinstance(raw_value, bool):
            return raw_value

        value = raw_value.strip().lower()

        if value in self.IS_SELECTED_VALUES:
            return True

        if value in self.IS_NOT_SELECTED_VALUES:
            return False

        self.print_value_warning(raw_value)
        return False

    def print_value_warning(self, raw_value: str):
        accepted_values_str = ", ".join(map(lambda val: f'"{val}"',
                                            self.ACCEPTED_VALUES))
        warning = dedent(f"""
            Option selection value "{raw_value}" not in accepted values: 
            {accepted_values_str}. Assuming not selected.
            """).replace("\n", "")
        print(warning)
