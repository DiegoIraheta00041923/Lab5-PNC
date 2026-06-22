package com.example.lab2;

import com.example.lab2.domain.entity.Hechicero;
import com.example.lab2.repository.HechiceroRepository;
import com.example.lab2.service.impl.HechiceroServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class HechiceroServiceImpTest {
    @Mock
    private HechiceroRepository hechiceroRepository;
    @InjectMocks
    private HechiceroServiceImplementation hechiceroServiceImp;

    @Test
    public void tryCreateHechicero(){

        Hechicero hechicero = new Hechicero();
        hechiceroServiceImp.createHechicero(hechicero);
        verify(hechiceroRepository).save(hechicero);
    }

    @Test
    public void shouldReturnAllHechiceros(){
        List<Hechicero> hechiceros = List.of(
                new Hechicero(),
                new Hechicero()
        );

        when(hechiceroRepository.findAll()).thenReturn(hechiceros);

        List<Hechicero> result = hechiceroServiceImp.getHechiceros();

        assertEquals(2,result.size());

        verify(hechiceroRepository).findAll();
    }

    @Test
    public void shouldReturnHechiceroWhenExists() {

        UUID id = UUID.randomUUID();

        Hechicero hechicero = new Hechicero();

        when(hechiceroRepository.findById(id))
                .thenReturn(Optional.of(hechicero));

        Hechicero result =
                hechiceroServiceImp.getHechiceroByID(id);

        assertNotNull(result);

        verify(hechiceroRepository).findById(id);
    }

    @Test
    public void shouldReturnNullWhenHechiceroDoesNotExist() {

        UUID id = UUID.randomUUID();

        when(hechiceroRepository.findById(id))
                .thenReturn(Optional.empty());

        Hechicero result =
                hechiceroServiceImp.getHechiceroByID(id);

        assertNull(result);

        verify(hechiceroRepository).findById(id);
    }

    @Test
    public void shouldUpdateHechicero() {

        UUID id = UUID.randomUUID();

        Hechicero existing = new Hechicero();
        existing.setName("Gojo");

        Hechicero updated = new Hechicero();
        updated.setName("Satoru Gojo");

        when(hechiceroRepository.findById(id))
                .thenReturn(Optional.of(existing));

        hechiceroServiceImp.updateHechiero(id, updated);

        assertEquals(
                "Satoru Gojo",
                existing.getName()
        );

        verify(hechiceroRepository).save(existing);
    }

    @Test
    public void shouldDeleteAndReturnHechicero() {

        UUID id = UUID.randomUUID();

        Hechicero hechicero = new Hechicero();

        when(hechiceroRepository.findById(id))
                .thenReturn(Optional.of(hechicero));

        Hechicero result =
                hechiceroServiceImp.deleteHechicero(id);

        assertEquals(hechicero, result);

        verify(hechiceroRepository)
                .deleteById(id);
    }

}
