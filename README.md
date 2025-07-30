# Socketbin

Socketbin is a minimalist clone of Pastebin, but instead of HTTP or REST APIs, it uses raw sockets and multithreaded request handling. It runs on a custom protocol called Simple Paste Request Protocol (SPRP).
## Simple Paste Request Protocol (SPRP)

SPRP defines a simple line-based structure for communication between clients and the server. All communication is text-based. The body uses xml formmating.

### Supported Request Types

`POST` – Create a new paste

`GET` – Retrieve a paste

`EDIT` – Modify an existing paste

`DELETE` – Remove a paste

`LOGIN` – Handle user login, sign-up, and logout

## Request & Response Format
### General Request Format

```
<REQUEST_TYPE> <PATH (if required)>
TOKEN <user-token>

<body>
```
- `PATH` is only required for GET, EDIT, and DELETE.
- `TOKEN` is required for all requests except LOGIN sign-in and LOGIN login.

### General Response Format

```
<REQUEST_TYPE> <STATUS_CODE>

<body>
```

## LOGIN Request

Handles user authentication and session management. Supports three subcommands:
### Sign-Up (sign-in)

Request:
```
LOGIN sign-in

<name>asafe</name>
<password>123456</password>
```
Success:
```
LOGIN 200
```
Failure (user already exists):
```
LOGIN 501
```
### Login (login)

Request:
```
LOGIN login

<name>asafe</name>
<password>123456</password>
```
Success:
```
LOGIN 200

<token>123e4567-e89b-42d3-a456-556642440000</token>
```
Failure (user not found):
```
LOGIN 502
```
Failure (wrong password):
```
LOGIN 503
```
### Logout (logout)

Request:
```
LOGIN logout
TOKEN 123e4567-e89b-42d3-a456-556642440000
```
Success:
```
LOGIN 200
```
Failure (invalid token):
```
LOGIN 504
```
## POST – Create a Paste

Request:
```
POST
TOKEN <user-token>

<title>My First Paste</title>
<content>Hello, world!</content>
```
Success:
```
POST 200

<id>/pastes/1234</id>
```
## GET – Retrieve a Paste

Request:
```
GET /pastes/1234
TOKEN <user-token>
```
Success:
```
GET 200

<title>My First Paste</title>
<content>Hello, world!</content>
```
Failure:
```
GET 404
```
## EDIT – Modify a Paste

Request:
```
EDIT /pastes/1234
TOKEN <user-token>

<title>Updated Title</title>
<content>Updated content.</content>
```
Success:
```
EDIT 200
```
Failure (no permission or not found):
```
EDIT 403
```
## DELETE – Remove a Paste

Request:
```
DELETE /pastes/1234
TOKEN <user-token>
```
Success:
```
DELETE 200
```
Failure (no permission or not found):
```
DELETE 403
```
### Status Codes

| Code | Meaning	                            | Applies To    |
|------|--------------------------------------|---------------|
| 200	 | OK / Success	                        | All           |
| 403	 | Forbidden (no permission)	          | EDIT, DELETE  |
| 404	 | Not Found	                          | GET           |
| 501	 | Sign-up failed (user already exists)	| LOGIN sign-in |
| 502	 | User not found	                      | LOGIN login   |
| 503	 | Incorrect password	                  | LOGIN login   |
| 504	 | Invalid or expired token	            | LOGIN logout  |

### Request types lookup table

| Request Type | Path Required | Token Required | Body Required | Description                  |
|--------------|---------------|----------------|----------------|-----------------------------|
| POST         | No            | Yes            | Yes            | Create a new paste          |
| GET          | Yes           | Yes            | No             | Retrieve a paste            |
| EDIT         | Yes           | Yes            | Yes            | Edit an existing paste      |
| DELETE       | Yes           | Yes            | No             | Delete an existing paste    |
| LOGIN sign-in| No            | No             | Yes            | Register a new user         |
| LOGIN login  | No            | No             | Yes            | Authenticate a user         |
| LOGIN logout | No            | Yes            | No             | Invalidate a session token  |



✅ TODOs

- Further specify request/response formatting rules (e.g., escaping, encoding)

- Add optional expiration field to pastes




