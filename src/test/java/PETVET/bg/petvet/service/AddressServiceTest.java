package PETVET.bg.petvet.service;

import PETVET.bg.petvet.model.entity.AddressEntity;
import PETVET.bg.petvet.repository.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    AddressRepository mockAddressRepo;

    private AddressService toTest;
    private AddressEntity address;

    @BeforeEach
    void setUp() {
        toTest = new AddressService(
                mockAddressRepo
        );
                address = new AddressEntity()
                        .setCity("Burgas")
                        .setCountry("Bulgaria")
                        .setStreet("Kiril i Metodii 7")
                        .setPostcode("12345");
    }

    @Test
    void testAddAddress_AddressAdded() {

        when(mockAddressRepo.save(any())).thenReturn(address);

        toTest.add(address);

        verify(mockAddressRepo,times(1)).save(address);

    }

    @Test
    void testFindByCityAndCountryAndStreet_AddressExists() {
        when(mockAddressRepo.findByCityAndCountryAndStreet(address.getCity(), address.getCountry(), address.getStreet())).
                thenReturn(Optional.of(address));

        Optional<AddressEntity> newAddress =  toTest.findByCityAndCountryAndStreet(address.getCity(), address.getCountry(), address.getStreet());

        Assertions.assertEquals(address.getCity(), newAddress.get().getCity());
        Assertions.assertEquals(address.getCountry(), newAddress.get().getCountry());
        Assertions.assertEquals(address.getStreet(), newAddress.get().getStreet());

    }
}
