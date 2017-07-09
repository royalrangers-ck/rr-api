# Create image based on the official java image from dockerhub
FROM java:8-jdk

# Setup environment
ENV JAR_NAME royal-rangers.jar
ENV GRADLE_VERSION 3.3
ENV GRADLE_HOME /usr/bin/gradle
ENV PATH $PATH:$GRADLE_HOME/bin

WORKDIR /usr/bin
RUN curl -sLO https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-all.zip && \
  unzip gradle-${GRADLE_VERSION}-all.zip && \
  ln -s gradle-${GRADLE_VERSION} gradle && \
  rm gradle-${GRADLE_VERSION}-all.zip

# Create a directory where our app will be placed
RUN mkdir -p /usr/src/app

# Change directory so that our commands run inside this new directory
WORKDIR /usr/src/app

# Install required dependencies
RUN apt-get update
RUN apt-get install -y curl
RUN apt-get install -y nano

# Get all the code needed to run the app
COPY . /usr/src/app

RUN gradle clean build

# Expose the port the app runs in
EXPOSE 8080

# Serve the app
ENTRYPOINT java -jar ./build/libs/${JAR_NAME} && /bin/bash