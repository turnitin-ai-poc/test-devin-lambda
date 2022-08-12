import typing as t

from textwrap import dedent
import json
import pathlib
import re

from jinja2.ext import Extension


COOKIECUTTER_PATH = pathlib.Path(__file__).parent / "cookiecutter.json"


class CookiecutterExtension(Extension):
    def __init__(self, environment):
        super().__init__(environment)

        with open(COOKIECUTTER_PATH, "r") as f:
            self.cookiecutter_config: dict = json.load(f)


class VariableSubstitutionExtension(CookiecutterExtension):
    DEFAULT_VARIABLE_START_STRING = "__TEMP"
    DEFAULT_VARIABLE_END_STRING = "TEMP__"

    def __init__(self, environment):
        super().__init__(environment)

        config = self.cookiecutter_config.get("_variable_substitution", {})
        start_str = config.get("variable_start_string",
                               self.DEFAULT_VARIABLE_START_STRING)
        end_str = config.get("variable_end_string",
                             self.DEFAULT_VARIABLE_END_STRING)
        self.var_regex =\
            re.compile(r"{}([\w\d_]*){}".format(start_str, end_str))

    def preprocess(
            self, source: str, name: t.Optional[str],
            filename: t.Optional[str] = None
    ) -> str:
        var_start_str = self.environment.variable_start_string
        var_end_str = self.environment.variable_end_string

        def replace_variable(match: t.Match) -> str:
            return f"{var_start_str} cookiecutter.{match[1]} {var_end_str}"

        return self.var_regex.sub(replace_variable, source)


class LineStatementSubstitutionExtension(CookiecutterExtension):
    DEFAULT_LINE_STATEMENT_PREFIX = "//TEMP"

    FALLBACK_ENV_LINE_STATEMENT_PREFIX = "#TEMP"

    def __init__(self, environment):
        super().__init__(environment)

        if environment.line_statement_prefix is None:
            environment.line_statement_prefix =\
                self.FALLBACK_ENV_LINE_STATEMENT_PREFIX

        config = \
            self.cookiecutter_config.get("_line_statement_substitution", {})
        line_stat_prefix = config.get("line_statement_prefix",
                                      self.DEFAULT_LINE_STATEMENT_PREFIX)
        self.line_statement_regex = \
            re.compile(r"^\s*{}(.*)$".format(line_stat_prefix), re.MULTILINE)

    def preprocess(
            self, source: str, name: t.Optional[str],
            filename: t.Optional[str] = None
    ) -> str:
        line_stat_prefix = self.environment.line_statement_prefix

        def replace_line_statement(match: t.Match) -> str:
            return f"{line_stat_prefix}{match[1]}"

        return self.line_statement_regex.sub(replace_line_statement, source)


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
