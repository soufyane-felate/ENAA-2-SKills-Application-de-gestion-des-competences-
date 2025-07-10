package org.enaaskills.apprenentservice;


import org.enaaskills.apprenentservice.Model.Apprenant;
import org.enaaskills.apprenentservice.Repository.ApprenantRepository;
import org.enaaskills.apprenentservice.Service.ApprenantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ApprenantServiceTest {

    @InjectMocks
    private ApprenantService apprenantService;

    @Mock
    private ApprenantRepository apprenantRepository;

    @Test
    public void testSaveApprenant() {
        Apprenant apprenant = new Apprenant();
        apprenant.setFirstName("John");
        when(apprenantRepository.save(apprenant)).thenReturn(apprenant);

        Apprenant savedApprenant = apprenantService.saveApprenant(apprenant);
        assertNotNull(savedApprenant);
        assertEquals("John", savedApprenant.getFirstName());
        verify(apprenantRepository, times(1)).save(apprenant);
    }



    @Test
    public void testGetAllApprenants() {
        Apprenant apprenant1 = new Apprenant();
        apprenant1.setFirstName("John");
        Apprenant apprenant2 = new Apprenant();
        apprenant2.setFirstName("Jane");
        when(apprenantRepository.findAll()).thenReturn(Arrays.asList(apprenant1, apprenant2));

        List<Apprenant> apprenants = apprenantService.getAllApprenants();
        assertNotNull(apprenants);
        assertEquals(2, apprenants.size());
        verify(apprenantRepository, times(1)).findAll();
    }

    @Test
    public void testGetApprenantById_found() {
        Apprenant apprenant = new Apprenant();
        apprenant.setId(1L);
        apprenant.setFirstName("John");
        when(apprenantRepository.findById(1L)).thenReturn(Optional.of(apprenant));

        Apprenant foundApprenant = apprenantService.getApprenantById(1L);
        assertNotNull(foundApprenant);
        assertEquals(1L, foundApprenant.getId());
        verify(apprenantRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetApprenantById_notFound() {
        when(apprenantRepository.findById(1L)).thenReturn(Optional.empty());

        Apprenant foundApprenant = apprenantService.getApprenantById(1L);
        assertNull(foundApprenant);
        verify(apprenantRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteApprenant() {
        doNothing().when(apprenantRepository).deleteById(1L);

        apprenantService.deleteApprenant(1L);
        verify(apprenantRepository, times(1)).deleteById(1L);
    }
}

