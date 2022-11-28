FROM node:18 AS builder-container
ADD . /app
WORKDIR /app
RUN npm install --omit=dev

FROM gcr.io/distroless/nodejs18-debian11 AS deploy-container
COPY --from=builder-container /app /app
WORKDIR /app
EXPOSE 3000
CMD ["hello_express.js"]
