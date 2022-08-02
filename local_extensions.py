import typing as t

import re
from jinja2.ext import Extension


class VariableSubstitutionExtension(Extension):
    var_subs_regex = re.compile(r"__TEMP([\w\d_]*)TEMP__")

    def preprocess(
            self, source: str, name: t.Optional[str],
            filename: t.Optional[str] = None
    ) -> str:
        var_start_str = self.environment.variable_start_string
        var_end_str = self.environment.variable_end_string

        def replace_variable(match):
            return "{} cookiecutter.{} {}".format(var_start_str, match[1],
                                                  var_end_str)

        return self.var_subs_regex.sub(replace_variable, source)
