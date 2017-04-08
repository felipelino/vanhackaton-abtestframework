

API Definiton


GET / POST /experiment
Description: this endpoint it is to create or update an Experiment. An Experiment should have two or more alternatives. 
Each alternative has a probability that can vary with a positive float number greather than ZERO until ONE. The sum of all probablities should be ONE. 
{
	"id":"experiment-id";
	"alternative" : [
		{
			"id" : "alternative-A",
			"probability": "0.5"
		},
		{
			"id" : "alternative-B",
			"probability": "0.5"
		}
	]
}



GET /experiment/{ID}/alternative/{alternativeId}
{
	"id" : "alternative-B",
	"probability": "0.5",
	"totalOccurrence" : 0
}


POST /experiment/{ID}/alternative/{alternativeId}/occurrence/{occurenceId}
Description: This endpoint creates a new ocurrence of an alternative. Optionally you can send some information about the occurrence.

Request:
{
	"data" : [
		"key1":"value1",
		"key2":"value2"
	] 
}

Response:
{
	"occurrenceId" : "1"
}

GET POST /experiment/{ID}/alternative/{alternativeId}/occurrence
Description: Get all occurrences of an alternative

POST /experiment/{ID}/alternative/{alternativeId}/occurrence/{occurenceId}/info
Description: Add a new data for a occurrence. This data is an array of key value. This could be helpfull to collect metrics for the test.

[
	"key1" :"value1",
	"key2" :"value2"
]
