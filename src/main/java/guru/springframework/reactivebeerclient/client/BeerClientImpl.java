package guru.springframework.reactivebeerclient.client;

import guru.springframework.reactivebeerclient.model.BeerDto;
import guru.springframework.reactivebeerclient.model.BeerPagedList;
import guru.springframework.reactivebeerclient.webClientConfig.WebClientProperties;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by jt on 3/13/21.
 */
@RequiredArgsConstructor
@Service
public class BeerClientImpl implements BeerClient {

	private final WebClient webClient;

	@Override
	public Mono<BeerDto> getBeerById(UUID id, Boolean showInventoryOnHand) {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path(WebClientProperties.BEER_V1_PATH)
						.queryParamIfPresent("showInventoryOnhand", Optional.ofNullable(showInventoryOnHand)).build(id))
				.retrieve().bodyToMono(BeerDto.class);
	}

	@Override
	public Mono<BeerPagedList> listBeers(Integer pageNumber, Integer pageSize, String beerName, String beerStyle,
			Boolean showInventoryOnhand) {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path(WebClientProperties.BEER_V1_PATH)
						.queryParamIfPresent("pageNumber", Optional.ofNullable(pageNumber))
						.queryParamIfPresent("pageSize", Optional.ofNullable(pageSize))
						.queryParamIfPresent("beerName", Optional.ofNullable(beerName))
						.queryParamIfPresent("beerStyle", Optional.ofNullable(beerStyle))
						.queryParamIfPresent("showInventoryOnhand", Optional.ofNullable(showInventoryOnhand)).build())
				.retrieve().bodyToMono(BeerPagedList.class);
	}

	@Override
	public Mono<ResponseEntity<Void>> createBeer(BeerDto beerDto) {
		return webClient.post().uri(uriBuilder -> uriBuilder.path(WebClientProperties.BEER_V1_PATH).build())
				.body(BodyInserters.fromValue(beerDto)).retrieve().toBodilessEntity();
	}

	@Override
	public Mono<ResponseEntity> updateBeer(BeerDto beerDto) {
		return null;
	}

	@Override
	public Mono<ResponseEntity> deleteBeerById(UUID id) {
		return null;
	}

	@Override
	public Mono<BeerDto> getBeerByUPC(String upc) {
		return null;
	}
}
