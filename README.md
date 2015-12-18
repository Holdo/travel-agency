curl -X POST -i -H "Content-Type: application/json" --data '{"dateFrom":"01-04-2016","dateTo":"02-04-2016","destination":"Bratislava, Slovakia","numberOfAvailable":"10","price":"450.00"}' http://localhost:8080/pa165/rest/trips/create
curl -X GET -i -H "Content-Type: application/json" http://localhost:8080/pa165/rest/trips/1
curl -X PUT -i -H "Content-Type: application/json" --data '{"dateFrom":"01-05-2016","dateTo":"12-05-2016","destination":"Brno, Czech republic","numberOfAvailable":"2","price":"150.00"}' http://localhost:8080/pa165/rest/trips/7
curl -X DELETE -i http://localhost:8080/pa165/rest/trips/7
curl -X GET -i -H "Content-Type: application/json" http://localhost:8080/pa165/rest/trips/7