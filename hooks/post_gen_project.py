import glob
import os
import shutil


def remove_empty_files_and_directories():
    for root, dirs, files in os.walk(".", topdown=False):
        for dir in dirs:
            dir_path = os.path.join(root, dir)
            if not os.listdir(dir_path):
                os.rmdir(dir_path)

        for file in files:
            file_path = os.path.join(root, file)
            if os.stat(file_path).st_size == 0:
                os.remove(file_path)


def remove_empty_projects():
    maven_project_directories = glob.glob("__TEMP__project_name_kebab_caseTEMP__-*/")
    for project_dir in maven_project_directories:
        java_src_path = os.path.join(project_dir, "src", "main", "java")
        if not os.path.exists(java_src_path):
            shutil.rmtree(project_dir)


def main():
    remove_empty_files_and_directories()
    remove_empty_projects()


if __name__ == "__main__":
    main()