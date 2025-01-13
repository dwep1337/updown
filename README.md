# Spring Boot + MinIO API

A simple Spring Boot application that demonstrates file upload and download functionalities using MinIO as an object storage solution.

## Features

- Upload files to a MinIO bucket.
- Download files from the MinIO bucket.

## Prerequisites

- **Java 21+**
- **Spring Boot 3.0.x**
- **MinIO Server** (or an S3-compatible object storage)
- **PostgreSQL** (as the database)
- **Maven** (for dependency management)

## Environment Variables

Create a `.env` file in the root directory of your project and configure it with the following values:

```plaintext
# MinIO Configuration
MINIO_ENDPOINT=http://localhost:9000    # URL of the MinIO server
MINIO_ACCESS_KEY=your-minio-access-key # Replace with your MinIO Access Key
MINIO_SECRET_KEY=your-minio-secret-key # Replace with your MinIO Secret Key

# PostgreSQL Configuration
DATABASE_URL=jdbc:postgresql://localhost:5432/your_database_name # PostgreSQL database URL
DATABASE_USER=your_database_user                                 # PostgreSQL user
DATABASE_PASSWORD=your_database_password                         # PostgreSQL password
```

## API Endpoints

### 1. **Upload File**

**POST** `/files`  
Uploads a file to the MinIO bucket.

#### Request
- **Headers:**  
  `Content-Type: multipart/form-data`

- **Body:**  
  A `multipart/form-data` request with the following fields:
  - `file` (required): The file to upload.
  - Any additional fields defined in the `FileUploadDTO`.

#### Response
- **200 OK:** Returns metadata of the uploaded file.
- **400 Bad Request:** If validation fails.

---

### 2. **Download File**

**GET** `/files/download/{referenceCode}`  
Downloads a file from the MinIO bucket using its reference code.

#### Request
- **Path Parameter:**  
  `referenceCode` (required): The unique identifier for the file to download.

#### Response
- **200 OK:** Returns the file as a stream.
- **404 Not Found:** If no file is found for the provided reference code.

---

 **Run the Application:**
   - Use Maven to build and run the application:
     ```bash
     ./mvnw spring-boot:run
     ```
