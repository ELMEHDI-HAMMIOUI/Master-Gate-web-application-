package master.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import master.beans.Master;

public class DatesChecker {
	public static boolean checkDateFinInsc(String d_fin_inscription) {
		//if the D_fin_inscription < now() then return false
		LocalDate d_fin = stringToDate( d_fin_inscription );
		if( datesDiffInDays(d_fin , LocalDate.now() ) >= 0) {
			return false;
		}
		//else, if the d_fin_inscription still not ended yet return true
		return true;
	}
	
	
    public static LocalDate stringToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(dateString!= null) return LocalDate.parse(dateString, formatter);
        return null;
        
    }
    
	//difference between two dates in days, useful for testing if the master due date is arrived 
    public static int datesDiffInDays(LocalDate firstDate, LocalDate secondDate) {
        if ((firstDate != null) && (secondDate != null)) {
            return Period.between(firstDate, secondDate).getDays();
        } else {
            return 0;
        }
    }
    
    
    
	public static boolean isMasterNew(String d_debut_inscription) {
		int NewDuration = 3; // la periode dont le master sera considerer comme 'new', est 7jours
		LocalDate d_debut = stringToDate( d_debut_inscription );
		LocalDate d_debut_after_week = d_debut.plusDays(NewDuration);
		
		//si aujourdhui est inferieur à la date de debut d'inscription + NewDuration
		//çad qu'on a pas passer NewDuration apres la date de debut 
		//donc le master est new donc true
		if( datesDiffInDays(LocalDate.now() , d_debut_after_week ) >= 0) {
			return true;
		}
		//else, si on a dépassé la date de debut par NewDuration alors false, le master n'est pas new
		return false;
	}
    
    
}
