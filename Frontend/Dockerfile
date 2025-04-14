FROM nginx:alpine

# Remove default nginx static content
RUN rm -rf /usr/share/nginx/html/*

# Copy your frontend files to nginx html directory
COPY main.html /usr/share/nginx/html/index.html
COPY script.js /usr/share/nginx/html/script.js
COPY styles.css /usr/share/nginx/html/styles.css
COPY styles-backup.css /usr/share/nginx/html/styles-backup.css

# Expose port 80
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]