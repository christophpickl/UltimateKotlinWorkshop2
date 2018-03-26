# API samples

## GET / accounts

Get all accounts.

Optional query parameter `alias`.

## GET / accounts/{id}

Get a specific account identified by its ID attribute.

## POST /accounts

Create a new account.

Request:
```json
{
	"alias": "alias1",
	"type": "CURRENT",
	"balance": 100
}
```

Response:
```json
{
    "id": 1,
    "alias": "alias1",
    "balance": 100,
    "type": "CURRENT"
}
```
