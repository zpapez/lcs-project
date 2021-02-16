# build environment
FROM node:13.12.0-alpine AS builder
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json ./
COPY package-lock.json ./
RUN npm ci --silent
RUN npm install react-scripts@4.0.2 -g --silent
COPY . ./
RUN npm run build

# production environment
FROM nginx:stable-alpine

COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/build /var/www

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
