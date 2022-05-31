# Challenge #3

Table of contents
1. [About The Project](#about-the-project)
    - [Adform's exam](#adforms-exam)
    - [Project description](#project-description)
    - [Thinking process](#thinking-process)
2. [Getting Started](#getting-started)
3. [Usage](#usage)
   - [Deployment with Docker and Minikube](#deployment-with-docker-and-minikube)
4. [Troubleshooting](#troubleshooting)


## About the Project

### Adform's exam
This project is a part of a final exam after 3-months of Scala Academy at [Adform](https://site.adform.com/). The exam consists of 7 exercises. You can navigate to them in Exam TOC below.

<details>
  <summary>Exam TOC</summary>

1. [Challenge #1](https://github.com/rafalkac02/adform-exam-1)
2. [Challenge #2](https://github.com/rafalkac02/adform-exam-2)
3. [Challenge #3](https://github.com/rafalkac02/adform-exam-3)
4. [Challenge #4](https://github.com/rafalkac02/adform-exam-4)
5. [Challenge #5](https://github.com/rafalkac02/adform-exam-5)
6. [Challenge #6](https://github.com/rafalkac02/adform-exam-6)
7. [Challenge #7](https://github.com/rafalkac02/adform-exam-7)
</details>

### Project description
Task description:
```text
Write a dockerized Scala application that reads a file from a directory and solves the following assignment: https://github.com/hubert-skowronek/scala-academy-dockerized-app

Expected results
- The results should be written to the plain text file.
- Deploy this Scala application to minikube.
- Write tests.
- Don’t forget about README with proper structure
- Publish to a github repo
```
Current version prints out ranking of fruits harvested sorted by amount.

### Thinking process
This challenge turned out to be time-consuming as using Spark and other frameworks is not allowed. 

I leave my thinking process notes here in case I don't fully meet the requirements:
1. **Most fruits gathered monthly** - I create the array of size 12 to store data for each month. I count the total amount for each fruit with this trick:
```scala
monthlyData(month-1) += (fruit -> (monthlyData(month-1).getOrElse(fruit, 0.0) + amount))
```
, where `monthlyData` is array for months and `month - 1` is index to save data to
2. **Are there employees better at gathering specific fruits?** 
I create map for each employee and count the amount of fruits harvested with getOrElse similarly to above code and then rank them.
3. **who contributed most to the income?** 
I create (person -> money) map and update it based on the fruit amount and price at the time.
Then rank them by the money collected.



## Getting Started
The project is built with:
- Scala
- sbt
- Docker

It is configured to be deployed on Minikube.

## Usage
- Clone the repository to the chosen directory with `git clone https://github.com/rafalkac02/adform-exam-3` command.
- Run the application with `sbt run` command in the same directory
- Test the application with `sbt test` command

### Deployment with Docker and Minikube

**Note:** this is supposed to work on **Windows**.

- **Create docker image**

In the project directory, run: 
```text
sbt docker
```
The docker image with customized name of `rafal-farm` will be created

- **push to minikube**

Start the minikube with
```
minikube start
```
Then, push the image to minikube:
```
minikube cache add rafal-farm
```

minikube refreshes the cache images on each start. However, to reload all the cached images on demand, run this command :
```
minikube cache reload
```

To display images you have added to the cache:
```
minikube cache list
```

To run the image, type
```
kubectl run hello-universe --image=rafal-farm:latest --port=8500
```

This listing will not include the images minikube’s built-in system images.
```
minikube cache delete rafal-farm
```

## Troubleshooting
- Make sure that your project does not contain outdated project files using:
```
sbt clean
```
- Make sure that your environment variables are set properly, including `JAVA_HOME`
