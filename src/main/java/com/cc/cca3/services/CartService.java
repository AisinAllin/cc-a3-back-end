package com.cc.cca3.services;

import com.cc.cca3.dtos.CartGetDto;
import com.cc.cca3.dtos.CartPostDto;
import com.cc.cca3.models.Cart;
import com.cc.cca3.models.MusicalInstrument;
import com.cc.cca3.models.UserEntity;
import com.cc.cca3.repositories.CartRepository;
import com.cc.cca3.repositories.MusicalInstrumentRepository;
import com.cc.cca3.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final MusicalInstrumentRepository musicalInstrumentRepository;

    public List<CartGetDto> getCartListByUserId(Long id) {
        List<Cart> cartList = cartRepository.findByUserEntity(id);
        return mapEntityToDot(cartList);
    }

    private List<CartGetDto> mapEntityToDot(List<Cart> cartList) {


        List<CartGetDto> result = cartList.stream().map(temp -> {
            CartGetDto cartGetDto = CartGetDto.builder()
                    .cartId(temp.getId())
                    .musicId(temp.getMusicalInstrument().getId())
                    .userId(temp.getUserEntity().getId())
                    .name(temp.getMusicalInstrument().getName())
                    .numRequire(temp.getNumRequire())
                    .price(temp.getMusicalInstrument().getPrice())
                    .build();
            return cartGetDto;
        }).collect(Collectors.toList());
        return result;
    }

    public CartGetDto saveInfoToCart(CartPostDto cartPostDto) {
        UserEntity user = userRepository.getById(cartPostDto.getUserId());
        MusicalInstrument mi = musicalInstrumentRepository.getById(cartPostDto.getMusicId());
        Cart savedCart = cartRepository.save(mapDtoToEntity(cartPostDto, mi, user));

        return mapEntityToDto(savedCart);
    }

    private Cart mapDtoToEntity(CartPostDto cartPostDto, MusicalInstrument mi, UserEntity user) {
        return Cart.builder()
                .userEntity(user)
                .musicalInstrument(mi)
                .numRequire(cartPostDto.getNumRequire())
                .build();
    }

    private CartGetDto mapEntityToDto(Cart cart) {
        return CartGetDto.builder()
                .cartId(cart.getId())
                .musicId(cart.getMusicalInstrument().getId())
                .userId(cart.getUserEntity().getId())
                .name(cart.getMusicalInstrument().getName())
                .numRequire(cart.getNumRequire())
                .price(cart.getMusicalInstrument().getPrice())
                .build();
    }
}
