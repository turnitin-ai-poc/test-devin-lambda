import typing as t

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
