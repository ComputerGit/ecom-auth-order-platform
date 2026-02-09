package com.at.t.eCommerce.boundedcontext.order;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.at.t.eCommerce.TestJwtFactory;

import jakarta.transaction.Transactional;

@SpringBootTest(properties = { "management.health.redis.enabled=false" })
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)

@Transactional
class OrderFlowIntegrationTest {

	@MockBean
	private RedisTemplate<String, String> redisTemplate;

	@MockBean
	private RedisConnectionFactory redisConnectionFactory;

	// <— add this
	@MockBean
	private org.springframework.data.redis.connection.ReactiveRedisConnectionFactory reactiveRedisConnectionFactory;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void place_order_works() throws Exception {
		String jwt = TestJwtFactory.createUserJwt("user-1");

		String body = """
				{
				  "items": [
				    {
				      "productId": "prod-1",
				      "name": "Phone",
				      "price": 500,
				      "quantity": 2
				    }
				  ]
				}
				""";

		mockMvc.perform(post("/orders").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON)
				.content(body)).andExpect(status().isOk()).andExpect(jsonPath("$.total").value(1000));
	}
}
