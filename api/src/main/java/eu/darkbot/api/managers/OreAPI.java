package eu.darkbot.api.managers;

import eu.darkbot.api.API;
import eu.darkbot.api.game.entities.Station;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

/**
 * Provides access to introspect &amp; interact with ores, like checking current amount or selling.
 */
public interface OreAPI extends API.Singleton {

    /**
     * @param ore or to check
     * @return amount of owned {@link Ore}
     */
    int getAmount(@NotNull Ore ore);

    /**
     * Sells the specified ore, trade window must be open for this method to work
     *
     * @param ore the {@code Ore} you want to sell it must be {@code sellable}
     * @see #showTrade
     * @see #canSellOres
     * @see Ore#sellable
     */
    void sellOre(@NotNull Ore ore);

    /**
     * Determines if ores can be sold based on if the ore trade window is open or not.
     *
     * @return true if ore trade window is open, false otherwise
     */
    boolean canSellOres();

    /**
     * Will either open or close the ore trade window based on the value of {@code show}
     *
     * @param show       true for showing ore trade window, false for closing ore trade window
     * @param tradePoint the {@code BasePoint} of the ore trader base station. For show(false) this is ignored.
     * @return true if ore trader window has been opened or closed and its animation is done,
     * false if animation is not done, or no action is needed to be taken
     * to change the visibility status of the ore trader window
     */
    boolean showTrade(boolean show, @Nullable("if show == false") Station.Refinery tradePoint);

    /**
     * Types of Ores visible in refinery window
     */
    @Getter
    @AllArgsConstructor
    enum Ore {
        PROMETIUM(0, false),
        ENDURIUM(1, false),
        TERBIUM(2, false),
        PROMETID(4, true),
        DURANIUM(5, true),
        PROMERIUM(6, true),
        SEPROM(7, true),
        PALLADIUM(8, false),
        OSMIUM(28, true),
        // Must keep at the end, otherwise selling code leaves a gap for it.
        XENOMIT(3, false, false);

        private final int id;
        private final boolean upgradable;
        private final boolean sellable;

        Ore(int id, boolean upgradable) {
            this(id, upgradable, true);
        }

        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }

    }
}
