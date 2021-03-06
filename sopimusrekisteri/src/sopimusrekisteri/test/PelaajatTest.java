package sopimusrekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import sopimusrekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.02.27 11:25:22 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PelaajatTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa39 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa39() throws SailoException {    // Pelaajat: 39
    Pelaajat pelaajat = new Pelaajat(); 
    Pelaaja p1 = new Pelaaja(), p2 = new Pelaaja(), p3 = new Pelaaja(); 
    assertEquals("From: Pelaajat line: 43", 0, pelaajat.getLkm()); 
    pelaajat.lisaa(p1); assertEquals("From: Pelaajat line: 44", 1, pelaajat.getLkm()); 
    pelaajat.lisaa(p2); assertEquals("From: Pelaajat line: 45", 2, pelaajat.getLkm()); 
    pelaajat.lisaa(p1); assertEquals("From: Pelaajat line: 46", 3, pelaajat.getLkm()); 
    assertEquals("From: Pelaajat line: 47", false, pelaajat.get(1) == p1); 
    assertEquals("From: Pelaajat line: 48", true, pelaajat.get(1) == p2); 
    try {
    assertEquals("From: Pelaajat line: 49", p2, pelaajat.get(3)); 
    fail("Pelaajat: 49 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    pelaajat.lisaa(p3); assertEquals("From: Pelaajat line: 50", 4, pelaajat.getLkm()); 
    pelaajat.lisaa(p3); assertEquals("From: Pelaajat line: 51", 5, pelaajat.getLkm()); 
    pelaajat.lisaa(p3); assertEquals("From: Pelaajat line: 52", 6, pelaajat.getLkm()); 
    p1.rekisteroi(); p2.rekisteroi(); 
    assertEquals("From: Pelaajat line: 54", true, pelaajat.get(0) == p1); 
    assertEquals("From: Pelaajat line: 55", false, pelaajat.get(1) == p1); 
    pelaajat.poista(p2); 
    assertEquals("From: Pelaajat line: 57", 5, pelaajat.getLkm()); 
    assertEquals("From: Pelaajat line: 58", true, pelaajat.get(1) == p1); 
  } // Generated by ComTest END
}