FROM node:18 AS build-container
ADD . /app
WORKDIR /app
RUN npm install --omit=dev

FROM gcr.io/distroless/nodejs18-debian11 AS deploy-container
COPY --from=build-container /app /app
WORKDIR /app
EXPOSE 3000
CMD ["hello_express.js"]
