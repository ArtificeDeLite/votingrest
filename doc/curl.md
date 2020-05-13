### curl samples (application deployed in application context `votingrest`).

### Anonymous authentication
##### register
`curl --location --request POST 'http://localhost:8080/votingrest/profile/register' --header 'Content-Type: application/json' --data-raw '{
     "name": "new User",
     "email": "newuser@yandex.ru",
 	"password": "123456"
 }'`

### Admin authentication

#### User
##### get All Users
`curl --location --request GET 'http://localhost:8080/votingrest/admin/users' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

##### get User 100000
`curl --location --request GET 'http://localhost:8080/votingrest/admin/users/100000' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

##### add new User
`curl --location --request POST 'http://localhost:8080/votingrest/admin/users' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' --header 'Content-Type: application/json' --data-raw '{
     "name": "User",
     "email": "user1@yandex.ru",
     "password": "newpassword"
 }'`
     
##### update User 100040
`curl --location --request PUT 'http://localhost:8080/votingrest/admin/users/100040' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' --header 'Content-Type: application/json' --data-raw '{
     "id": 100040,
     "name": "newUser",
     "email": "user1@yandex.ru",
     "password": "password"
 }'`
 
##### delete User 100040
`curl --location --request DELETE 'http://localhost:8080/votingrest/admin/users/100040' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

#### Restaurant
##### get All Restaurants
`curl --location --request GET 'http://localhost:8080/votingrest/admin/restaurants' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

##### get Restaurant
`curl --location --request GET 'http://localhost:8080/votingrest/admin/restaurants/100005' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

##### create Restaurant
`curl --location --request POST 'http://localhost:8080/votingrest/admin/restaurants' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' --header 'Content-Type: application/json' --data-raw '{
     "description": "new Restaurant"
 }'`

##### update Restaurant 100050
`curl --location --request PUT 'http://localhost:8080/votingrest/admin/restaurants/100041' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' --header 'Content-Type: application/json' --data-raw '{
     "id": 100041,
     "description": "updated Restaurant"
 }'`

#### Dishes
##### get Menu for Restaurant 100002
`curl --location --request GET 'http://localhost:8080/votingrest/admin/restaurants/100002/menu' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

##### get Menu for Restaurant 100002 for date 2020-01-31
`curl --location --request GET 'http://localhost:8080/votingrest/admin/restaurants/100002/menu?date=2020-01-31' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
##### get Dish 100022
`curl --location --request GET 'http://localhost:8080/votingrest/admin/restaurants/100002/menu/100022' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

##### create Menu for Restaurant 100002
`curl --location --request POST 'http://localhost:8080/votingrest/admin/restaurants/100002/menu' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' --header 'Content-Type: application/json' --data-raw '[
     {
         "description": "soup",
         "price": 25000,
         "date": "2020-05-05"
     },
     {
         "description": "main dish",
         "price": 45000,
         "date": "2020-05-05"
     },
     {
         "description": "tea",
         "price": 5000,
         "date": "2020-05-05"
     }
 ]'`

##### update Dish 100042
`curl --location --request PUT 'http://localhost:8080/votingrest/admin/restaurants/100002/menu/100042' --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' --header 'Content-Type: application/json' --data-raw '{
         "id": 100042,
         "description": "updated soup",
         "price": 25000,
         "date": "2020-05-05"
     }'`

### User authentication

#### User
##### get profile
`curl --location --request GET 'http://localhost:8080/votingrest/profile' --header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

##### update profile
`curl --location --request PUT 'http://localhost:8080/votingrest/profile' --header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' --header 'Content-Type: application/json' --data-raw '{
     "id": 100000,
     "name": "User updated",
     "email": "user@yandex.ru",
 	"password": "password"
 }'`

#### Restaurant
##### get All Restaurants with menu for today
`curl --location --request GET 'http://localhost:8080/votingrest/restaurants' --header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

##### get Restaurant
`curl --location --request GET 'http://localhost:8080/votingrest/restaurants/100002' --header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

#### Dishes
##### get All Dishes by Restaurant 100002 for today
`curl --location --request GET 'http://localhost:8080/votingrest/restaurants/100002/menu' --header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

##### get Dish 100022
`curl --location --request GET 'http://localhost:8080/votingrest/restaurants/100002/menu/100022' --header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

#### Vote
##### get All history Votes for User
`curl --location --request GET 'http://localhost:8080/votingrest/votes' --header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

##### get Vote
`curl --location --request GET 'http://localhost:8080/votingrest/votes/100037' --header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

##### create Vote for Restaurant 100005
`curl --location --request POST 'http://localhost:8080/votingrest/votes?restaurantId=100005' --header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

##### update Vote 100040 
`curl --location --request PUT 'http://localhost:8080/votingrest/votes/100040?restaurantId=100004' --header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`





