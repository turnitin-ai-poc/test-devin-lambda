# Contributing to Panda templates

:tada: First and foremost, thank you for taking the time to contribute! :tada:

The TRA templates exist to make creating lambdas easier for all developers, and part of that involves some guidelines
for how things ought to work. Some are based on hard-learned lessons and experience, while others are somewhat arbitrary
for the sake of consistency. Please don't assume these are all hard and fast rules that shouldn't be questioned! But at
the same time, if you want to do things differently, please reach out and talk about why.

## Questions, Comments, Concerns

The Slack channel `#tra-templates` is a good place to reach people who knows something about this
code. Alternatively, we can be reached directly on Slack as `@Phillip Winn`, `@Beth Stevens`, or `@Marco Romanini`.

## Getting Started

The key to the TRA templates is that they can be built and tested directly using `mvn clean package` in a directory
with a `pom.xml` file. Usually this is
`_temp_` or some variation on that. This should make it easier to make changes to than many templates which require a
generation step before they'll compile.

The `.gitignore` file is set up to ensure that IntelliJ IDEA files don't make it into the repos. If your editor or IDE
of choice isn't excluded, please update
`.gitignore`! While we depend on Maven as a build system, we want to be agnostic about editors/IDEs.

Some information about each template is included in the README.md files for the template, while additional information
about the project can be found on Confluence
as [TRA Templates](https://turnitin.atlassian.net/wiki/spaces/refarch/pages/94080598036/TRA+Templates)

## How Can I Contribute?

### Reporting a Bug

Post in the `#tra-templates` Slack channel. We will need to refine this process so that it scales!

### Suggesting a Feature/Enhancement

Post in the `#tra-templates` Slack channel. We will need to refine this process so that it scales!

### Contributing Code

Pull Requests are very welcome! While the goal is to keep these templates small and fast, covering the most basic lambda
types without generating a lot of code that will end up being deleted, needs change over time, and we haven't yet
covered all common lambda types. If you know how you want to contribute, reach out via Slack and let's talk about it! If
you want to contribute but don't yet know exactly how, reach out via Slack and let's talk about that, too!

**Super Important**  
This repo is *mirrored* in both GitHub Enterprise and BitBucket until such a time as we coalesce into a single
repository.  Therefore it is extremely important that when making contributions they be made to both repositories.  This can
be done quite easily with these steps:

    > git clone git@bitbucket.org:examsoft/tra-template-lambda-http-java.git
    > cd tra-template-lambda-http
    > git remote set-url --add --push origin git@bitbucket.org:examsoft/tra-template-lambda-http-java.git
    > git remote set-url --add --push origin git@ghe.iparadigms.com:AppOps/tra-template-lambda-http-java.git

You can verify you did everything correctly as follows:

    > git remote -v
    origin	git@bitbucket.org:examsoft/tra-template-lambda-http-java.git (fetch)
    origin	git@ghe.iparadigms.com:AppOps/tra-template-lambda-http-java.git (push)
    origin	git@bitbucket.org:examsoft/tra-template-lambda-http-java.git (push)
