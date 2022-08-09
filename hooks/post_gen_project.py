import glob
import shutil
import xml.etree.ElementTree as XMLElementTree


def find_all_maven_projects():
    maven_project_directories = glob.glob("__TEMP__project_name_kebab_caseTEMP__-*/")
    return set(dir_name.rstrip("/") for dir_name in maven_project_directories)


def find_included_maven_projects():
    tree = XMLElementTree.parse("pom.xml")
    root = tree.getroot()
    modules = root.find("pom:modules",
                        {"pom": "http://maven.apache.org/POM/4.0.0"})
    return [module.text for module in modules]


def main():
    all_maven_projects = find_all_maven_projects()
    required_maven_projects = find_included_maven_projects()
    maven_projects_to_remove = all_maven_projects - set(required_maven_projects)
    for project in maven_projects_to_remove:
        shutil.rmtree(project)


if __name__ == "__main__":
    main()