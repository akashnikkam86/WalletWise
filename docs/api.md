# WalletWise API Documentation

## Base URL
- Local: `http://localhost:8081`
- Production: `https://walletwise-production.up.railway.app`

---

## Authentication

### Register
`POST /register`

| Parameter | Type | Required |
|-----------|------|----------|
| username | String | Yes |
| email | String | Yes |
| password | String | Yes |

### Login
`POST /login`

| Parameter | Type | Required |
|-----------|------|----------|
| username | String | Yes |
| password | String | Yes |

### Logout
`GET /logout`

---

## Expenses

### View Dashboard
`GET /dashboard`

### Add Expense
`POST /expenses/add`

| Parameter | Type | Required |
|-----------|------|----------|
| title | String | Yes |
| amount | Decimal | Yes |
| category | String | Yes |
| expenseDate | Date | Yes |
| note | String | No |

### Edit Expense
`GET /expenses/edit/{id}`
`POST /expenses/edit/{id}`

### Delete Expense
`POST /expenses/delete/{id}`

---

## Budget

### View Budget Page
`GET /budget`

### Set Budget
`POST /budget`

| Parameter | Type | Required |
|-----------|------|----------|
| monthlyLimit | Decimal | Yes |

---

## Categories
`Food` `Transport` `Shopping` `Bills` `Health` `Other`
