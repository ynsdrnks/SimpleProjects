package com.ynsdrnks.simplejpaonetoone.country;


import com.ynsdrnks.simplejpaonetoone.entity.Country;
import com.ynsdrnks.simplejpaonetoone.repository.CountryRepository;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.junit.Assert;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@NoArgsConstructor
@TestPropertySource(locations = "classpath:test.properties")
public class CountryRepositoryTests extends Assert {
    @Qualifier("countryRepository")
    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void testCreateNewCountry(){
        Country country = new Country();
        country.setCountryName("Vietnam");
        countryRepository.save(country);
    }
}