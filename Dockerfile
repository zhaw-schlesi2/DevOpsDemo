FROM azul/zulu-openjdk:17
RUN apt-get update && apt-get install -y curl \
  && curl -sL https://deb.nodesource.com/setup_18.x | bash - \
  && apt-get install -y nodejs \
  && curl -L https://www.npmjs.com/install.sh | sh

WORKDIR /usr/src/app

COPY . .

ENV NODE_OPTIONS=--openssl-legacy-provider
RUN cd frontend && npm install
RUN cd frontend && npm run build
RUN cd backend && chmod +x gradlew
RUN cd backend && ./gradlew build

EXPOSE 4567
CMD ["java", "-cp", "/usr/src/app/backend/build/libs/devops-all.jar", "ch.zhaw.iwi.devops.Main"]