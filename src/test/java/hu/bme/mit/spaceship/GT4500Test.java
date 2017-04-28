package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockPrimaryStore;
  private TorpedoStore mockSecondaryStore;

  @Before
  public void init(){
	mockPrimaryStore = mock(TorpedoStore.class);
	mockSecondaryStore = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPrimaryStore, mockSecondaryStore);
  }

  @Test
  public void fireTorpedos_Single_Success(){
    // Arrange
	when(mockPrimaryStore.fire(1)).thenReturn(true);
	when(mockSecondaryStore.fire(1)).thenReturn(true);
    
	  
	// Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);
	
    // Assert
    verify(mockPrimaryStore, times(1)).fire(1);
    verify(mockSecondaryStore, times(0)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_All_Success(){
    // Arrange
	when(mockPrimaryStore.isEmpty()).thenReturn(false);
	when(mockSecondaryStore.isEmpty()).thenReturn(false);
	when(mockPrimaryStore.fire(1)).thenReturn(true);
	when(mockSecondaryStore.fire(1)).thenReturn(true);
	
		
    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    verify(mockPrimaryStore, times(1)).fire(1);
    verify(mockSecondaryStore, times(1)).fire(1);
    assertEquals(true, result);
  }

}
