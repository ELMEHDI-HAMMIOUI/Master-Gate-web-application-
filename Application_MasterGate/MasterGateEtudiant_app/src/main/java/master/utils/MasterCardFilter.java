package master.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import master.beans.MasterCard;

public class MasterCardFilter {
	
	//method to sort date (date de debut d'inscription in descending order[if you want ascending order then change the order of mc1 and mc2])
    public static void sortMasterCardsByDebutInscriptionDescending(List<MasterCard> masterCards) {
        Collections.sort(masterCards, new Comparator<MasterCard>() {
            @Override
            public int compare(MasterCard mc1, MasterCard mc2) {
                return mc2.getD_debut_inscription().compareTo(mc1.getD_debut_inscription());
            }
        });
    }
    
	//method to sort date (date de debut d'inscription in ascending order)
    public static void sortMasterCardsByDebutInscriptionAscending(List<MasterCard> masterCards) {
        Collections.sort(masterCards, new Comparator<MasterCard>() {
            @Override
            public int compare(MasterCard mc1, MasterCard mc2) {
                return mc1.getD_debut_inscription().compareTo(mc2.getD_debut_inscription());
            }
        });
    }
	
	//method to sort date (date de fin d'inscription in ascending order[if you want descending order then change the order of mc1 and mc2])
    public static void sortMasterCardsByFinInscriptionAscending(List<MasterCard> masterCards) {
        Collections.sort(masterCards, new Comparator<MasterCard>() {
            @Override
            public int compare(MasterCard mc1, MasterCard mc2) {
                return mc1.getD_fin_inscription().compareTo(mc2.getD_fin_inscription());
            }
        });
    }
    
	//method to sort date (date de fin d'inscription in descending order)
    public static void sortMasterCardsByFinInscriptionDescending(List<MasterCard> masterCards) {
        Collections.sort(masterCards, new Comparator<MasterCard>() {
            @Override
            public int compare(MasterCard mc1, MasterCard mc2) {
                return mc2.getD_fin_inscription().compareTo(mc1.getD_fin_inscription());
            }
        });
    }
    
    //get just the new masters
    public static List<MasterCard> getNewMasterCards(List<MasterCard> masterCards) {
        List<MasterCard> newMasterCards = new ArrayList<>();
        for (MasterCard mc : masterCards) {
            if (mc.isNewMaster()) {
                newMasterCards.add(mc);
            }
        }
        return newMasterCards;
    }
    
    //get not expired mastercards
    public static List<MasterCard> getNotExpiredMasterCards(List<MasterCard> masterCards) {
        List<MasterCard> notExpiredMasterCards = new ArrayList<>();
        for (MasterCard mc : masterCards) {
            if (!mc.isExpired()) {
                notExpiredMasterCards.add(mc);
            }
        }
        return notExpiredMasterCards;
    }

    //get expired mastercards
    public static List<MasterCard> getExpiredMasterCards(List<MasterCard> masterCards) {
        List<MasterCard> expiredMasterCards = new ArrayList<>();
        for (MasterCard mc : masterCards) {
            if (mc.isExpired()) {
                expiredMasterCards.add(mc);
            }
        }
        return expiredMasterCards;
    }
    
    public static List<MasterCard> getMasterCardsByCities(List<MasterCard> masterCards, List<String> cities) {
        List<MasterCard> filteredMasterCards = new ArrayList<>();
        for (MasterCard mc : masterCards) {
            if (cities.contains(mc.getVille())) {
                filteredMasterCards.add(mc);
            }
        }
        return filteredMasterCards;
    }
    
    public static List<MasterCard> getMasterCardsBySpecialities(List<MasterCard> masterCards, List<String> specialities) {
        List<MasterCard> filteredMasterCards = new ArrayList<>();
        for (MasterCard mc : masterCards) {
            if (specialities.contains(mc.getSpecialite())) {
                filteredMasterCards.add(mc);
            }
        }
        return filteredMasterCards;
    }
    
    public static List<MasterCard> getMasterCardsByFaculties(List<MasterCard> masterCards, List<String> faculties) {
        List<MasterCard> filteredMasterCards = new ArrayList<>();
        for (MasterCard mc : masterCards) {
            if (faculties.contains(mc.getNom_fac())) {
                filteredMasterCards.add(mc);
            }
        }
        return filteredMasterCards;
    }
    
    public static List<MasterCard> getMasterCardsByUniversities(List<MasterCard> masterCards, List<String> universities) {
        List<MasterCard> filteredMasterCards = new ArrayList<>();
        for (MasterCard mc : masterCards) {
            if (universities.contains(mc.getNom_uni())) {
                filteredMasterCards.add(mc);
            }
        }
        return filteredMasterCards;
    }
}
