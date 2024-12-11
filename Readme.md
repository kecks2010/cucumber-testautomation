# Cucumber Testautomation

This is an example implementation of a cucumber test automation for this [Test Shopsystem](https://www.askomdch.com).

## Precondition
To run the test automation, you need the following system requirements:

* Java 23
* Maven
* git
* Chrome

After that, you have to clone the testdata project first.

    cd <project_folder>
    git clone https://github.com/kecks2010/testdata.git
    cd testdata
    git branch cucumber_test origin/cucumber_test
    git checkout cucumber_test
    git status

You should get the following output:

    On branch cucumber_test
    Your branch is up to date with 'origin/cucumber_test'.

    nothing to commit, working tree clean
    
And now, you have to install the project to your local maven repository.

    mvn clean install

The preconditions are ready, so that you can continue with test automation project.

## Cucumber-Tests
After the installation of the testdata project, you can clone the cucumber test automation project.

    cd <project_folder>
    git clone https://github.com/kecks2010/cucumber-testautomation.git
    cd cucumber-testautomation

Now the test automation is downloaded and if you got no problems, it should work.

    mvn clean test

Maven starts the test automation headless and parallel with the Chrome browser on the default environment. To start the
test automation with firefox, you need the following Maven command:

    mvn clean test -Dbrowser=firefox

The result of the test run, you find the report under:

    target/cucumber-reports/Cucumber.html
