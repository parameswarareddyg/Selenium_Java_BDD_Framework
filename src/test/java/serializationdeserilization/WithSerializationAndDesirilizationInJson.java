package serializationdeserilization;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class WithSerializationAndDesirilizationInJson {
//	 {
//	        "id": 1,
//	        "name": "Leanne Graham",
//	        "username": "Bret",
//	        "email": "Sincere@april.biz",
//	        "address": {
//	            "street": "Kulas Light",
//	            "suite": "Apt. 556",
//	            "city": "Gwenborough",
//	            "zipcode": "92998-3874",
//	            "geo": {
//	                "lat": "-37.3159",
//	                "lng": "81.1496"
//	            }
//	        },
//	        "phone": "1-770-736-8031 x56442",
//	        "website": "hildegard.org",
//	        "company": {
//	            "name": "Romaguera-Crona",
//	            "catchPhrase": "Multi-layered client-server neural-net",
//	            "bs": "harness real-time e-markets"
//	        }
//	    },

	@Test
	public void postUserDetails() {
		Geo geo = new Geo();
		geo.setLat("-37.3159");
		geo.setLng("81.1496");

		Address address= new Address();
		address.setStreet("Kulas Light");
		address.setSuite("Apt. 556");
		address.setCity("Gwenborough");
		address.setZipcode("92998-3874");
		address.setGeo(geo);

		PersonDetails person = new PersonDetails();
		person.setId(101);
		person.setName("Leanne Graham");
		person.setUsername("Bret");
		person.setEmail("Sincere@april.biz");
		person.setPhone("1-770-736-8031 x56442");
		person.setWebsite("hildegard.org");
		
		Company company = new Company();
		company.setName("Romaguera-Crona");
		company.setCatchPhrase("Multi-layered client-server neural-net");
		company.setBs("harness real-time e-markets");
		
		person.setCompany(company);

		given()
		.baseUri("https://jsonplaceholder.typicode.com")
		.contentType(ContentType.JSON)
		.body(person)
		.when()
		.post("/users")
		.then()
		.statusCode(201);
	}

	@Test
	public void getUserDetails()
	{
		PersonDetails person = get("https://jsonplaceholder.typicode.com/users/1").as(PersonDetails.class);
		person.displayRecord();
		Assert.assertEquals(person.getId(), 1);
	}
}
