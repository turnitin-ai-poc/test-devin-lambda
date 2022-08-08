import re
import sys
from textwrap import dedent

PROJECT_NAME_REGEX = r"^[^\W0-9][\w\s\- ]*$"
project_name = "{{ cookiecutter.project_name }}"
if not re.match(PROJECT_NAME_REGEX, project_name):
    message = dedent(f"""
    {project_name} is not a valid project name. Project names must
    * start with a letter (lowercase or uppercase)
    * contain only alphanumeric characters, spaces and "-"s.
    """)
    print(message)
    sys.exit(1)
