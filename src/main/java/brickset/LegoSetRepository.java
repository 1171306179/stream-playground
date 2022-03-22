package brickset;

import repository.Repository;

import java.util.Objects;

/**
 * Represents a repository of {@code LegoSet} objects.
 *
 * @author Zhang Haoyang
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }


    /**
     * Returns the number of Theme which is City.
     *
     * @param Theme the theme of LegoSets
     * @return value the number of Theme which is City
     */
    public long countTheme(String Theme) {
        return getAll().stream().
                filter(LegoSet -> LegoSet.getTheme() != null && LegoSet.getTheme().equals(Theme)).
                count();

    }

    /**
     * Returns the name of LegoSets which pieces below 100.
     *
     * @return String
     */
    public void LegoSetWithPieces() {
        getAll().stream().
                filter(LegoSet -> LegoSet.getPieces() < 100).
                map(LegoSet::getName).
                forEach(System.out::println);
    }

    /**
     * Returns the number of Dimensions which are null.
     *
     * @return value the number of Dimensions which are null
     */
    public long NumberOfNullDimensions(){
        return getAll().stream().
                filter(LegoSet -> LegoSet.getDimensions() == null).
                map(LegoSet::getDimensions).
                count();

    }

    /**
     * Returns the name of LegoSets which starts with B.
     *
     * @return String
     */
    public void NameOfLegoSets(){
        getAll().stream().
                filter(LegoSet -> LegoSet.getName().startsWith("B")).
                map(LegoSet::getName).
                forEach(System.out::println);
    }

    /**
     * Returns the first 10 Year of LegoSets.
     *
     * @return String
     */
    public void YearOfLegoSets(){
        (getAll().stream().
                map(LegoSet::getYear)).
                limit(10).
                forEach(System.out::println);
    }


    public static void main(String[] args) {

        var repository = new LegoSetRepository();

        //Method 1
        System.out.printf("%d",repository.countTheme("City"));
        System.out.println("==========================");

        //Method 2
        repository.LegoSetWithPieces();
        System.out.println("==========================");

        //Method 3
        System.out.printf("%d",repository.NumberOfNullDimensions());
        System.out.println("==========================");

        //Method 4
        repository.NameOfLegoSets();
        System.out.println("==========================");

        //Method 5
        repository.YearOfLegoSets();
        System.out.println("==========================");



    }

}
