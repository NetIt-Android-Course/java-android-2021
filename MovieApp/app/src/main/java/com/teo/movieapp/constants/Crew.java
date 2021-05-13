package com.teo.movieapp.constants;

public enum Crew {

    TROY(new String[]{"Brian Cox as Agamemnon", "Brad Pitt as Achilles", "Eric Bana as Hector",
            "Orlando Bloom as Paris  ", "Siri Svegler as Polydora", "Julian Glover as Triopas"}),

    AVATAR(new String[]{"Sam Worthington as Jake Sully", "Sigourney Weaver as Dr. Grace Augustine",
            "Stephen Lang as Colonel Miles Quaritch", "Michelle Rodriguez as Trudy Chacón",
            "Giovanni Ribisi as Parker Selfridge", "Joel David Moore as Norm Spellman "}),

    TAKEN(new String[]{"Liam Neeson as Bryan Mills", "Maggie Grace as Kim", "Jon Gries as Casey",
            "Leland Orser as Sam", "Famke Janssen as Lenore"}),

    MORTAL_KOMBAT(new String[]{"Jessica McNamee as Sonya Blade", "Hiroyuki Sanada as Hanzo Hasashi / " +
            "Scorpion", "Josh Lawson as Kano", "Joe Taslim as Bi-Han / Sub-Zero", "Ludi Lin as Liu Kang",
            "Tadanobu Asano as Raiden", "Mehcad Brooks as Major Jackson \'Jax\' Briggs", "Chin Han as Shang Tsung" }),

    BAD_BOYS(new String[]{"Martin Lawrence as Marcus Burnett", "Will Smith as Mike Lowrey",
            "Emmanuel Xuereb as Eddie Dominguez", "Lisa Boyle as Girl Decoy", "Frank John Hughes as Casper"}),

    GODZILLA_VS_KONG(new String[]{"Alexander Skarsgård as Nathan Lind", "Millie Bobby Brown as Madison Russell",
            "Rebecca Hall as Ilene Andrews", "Brian Tyree Henry as Bernie Hayes", "Shun Oguri as Ren Serizawa",
            "Eiza González as Maya Simmons", "Julian Dennison as Josh Valentine", "Lance Reddick as Monarch Director",
            "Kyle Chandler as Mark Russell"}),

    ZACK_SNYDER(new String[]{"Ben Affleck as Batman / Bruce Wayne",
            "Henry Cavill as Superman / Clark Kent", "Amy Adams as Lois Lane", "Gal Gadot as Wonder Woman / Diana Prince",
            "Ray Fisher as Cyborg / Victor Stone", "Jason Momoa as Aquaman / Arthur Curry", "Ezra Miller as The Flash / Barry Allen",
            "Willem Dafoe as Vulko", "Jesse Eisenberg\tJesse Eisenberg as Lex Luthor"}),

    RUSH_HOUR_3(new String[]{"Chris Tucker as Carter", "Jackie Chan as Lee", "Max von Sydow as Reynard",
            "Hiroyuki Sanada as Kenji", "Yvan Attal as George", "Yûki Kudô as Dragon Lady"}),

    CLASH_OF_TITANS(new String[]{"Sam Worthington as Perseus", "Liam Neeson as Zeus",
            "Ralph Fiennes as Hades", "Jason Flemyng as Calibos / Acrisius",
            "Gemma Arterton as Io", "Alexa Davalos as Andromeda", "Tine Stapelfeldt as Danae",
            "Mads Mikkelsen as Draco", "Luke Evans as Apollo", "Izabella Miko as Athena",
            "Liam Cunningham as Solon", "Hans Matheson as Ixas", "Ashraf Barhom as Ozal"}),

    GLITTER(new String[]{"Mariah Carey as Billie Frank", "Max Beesley as Julian Dice",
            "Da Brat as Louise" , "Tia Texada as Roxanne" , "Valarie Pettiford as Lillian Frank",
            "Ann Magnuson as Kelly"})


    ;

    Crew(String[] actors) {
        this.actors = actors;
    }

    private final String[] actors;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String actor : actors) {
             result.append(actor).append("\n");
        }
        return result.toString();
    }
}
