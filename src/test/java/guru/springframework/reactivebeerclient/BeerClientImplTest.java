package guru.springframework.reactivebeerclient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import guru.springframework.reactivebeerclient.client.BeerClientImpl;
import guru.springframework.reactivebeerclient.model.BeerDto;
import guru.springframework.reactivebeerclient.model.BeerPagedList;
import guru.springframework.reactivebeerclient.model.v2.BeerStyleEnum;
import guru.springframework.reactivebeerclient.webClientConfig.WebClientConfig;
import reactor.core.publisher.Mono;

class BeerClientImplTest {

	BeerClientImpl beerClient;

	@BeforeEach
	void setUp() {
		beerClient = new BeerClientImpl(new WebClientConfig().webClient());
	}

	@Test
	void testGetBeerById() {

	}

	@Test
	void testListBeers() {
		Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(1, 10, null, null, false);

		BeerPagedList pagedList = beerPagedListMono.block();

		assertThat(pagedList).isNotNull();
		assertThat(pagedList.getContent().size()).isEqualTo(10);
		System.out.println(pagedList.toList());
	}

	@Test
	void testCreateBeer() {

		BeerDto beerDto = BeerDto.builder().beerName("Akka mala").beerStyle(BeerStyleEnum.LAGER.name())
				.quantityOnHand(11)
				.upc("12121212")
				.createdDate(OffsetDateTime.now()).price(new BigDecimal(111.10)).build();
		Mono<ResponseEntity<Void>> responseMono = beerClient.createBeer(beerDto);
		ResponseEntity<?> responseEntity = responseMono.block();
		assertThat(responseEntity.getStatusCode().equals(HttpStatus.CREATED));

	}

	@Test
	void testUpdateBeer() {

	}

	@Test
	void testDeleteBeerById() {

	}

	@Test
	void testGetBeerByUPC() {

	}

	@Test
	void testBeerClientImpl() {

	}

}
