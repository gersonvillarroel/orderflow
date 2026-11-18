FROM node:22-alpine AS deps
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm ci

FROM deps AS dev
COPY . .
EXPOSE 4200
# --poll: detecta cambios en archivos montados desde Windows
CMD ["npx", "ng", "serve", "--host", "0.0.0.0", "--poll", "2000"]
