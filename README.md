# Socketbin

Socketbin is a simple copy of pastebin except it only uses sockets and threads for handling requests.

---

## Protocol

For simplicity im gonna write a simple protocol, im gonna call it Very Simple Request Protocol (VSRP).
Its gonna have 5 diferent types of requests:

- `GET`
- `POST`
- `EDIT`
- `DELETE`
- `LOGIN`

Besides the `POST` and `LOGIN` request, every other request is gonna have a path attached to it:
```
REQUEST PATH
...
```
---

### Request structure

every request are gonna have a similar structure like this:
```
<request type> <request path(if needed)>
TOKEN <user-token>

body
```

and its response:
```
<request type> <status-code>

body
```

### `LOGIN` request
the `LOGIN` request is a special request that sends a sign-in, login and logout request:
sign-in example:
```
LOGIN sign-in

<name>asafe</name>
<password>123456</password>
```
sucess request:
```
LOGIN 200
```
failure request:
```
LOGIN 501
```
`501` status code means that already exists a user with that name.

login example:
```
LOGIN login

<name>asafe</name>
<password>123456</password>
```
success request:
```
LOGIN 200

<token>123e4567-e89b-42d3-a456-556642440000</token>
```

user not found failure request:
```
LOGIN 502
```
wrong password failure request:
```
LOGIN 503
```

logout example:
```
LOGIN logout
TOKEN 123e4567-e89b-42d3-a456-556642440000
```

success request:
```
LOGIN 200
```
not such token exists failure request:
```
LOGIN 504
```

# TODOS

- see if i need more specification of the request rules.
- standardize the requests in a table.




