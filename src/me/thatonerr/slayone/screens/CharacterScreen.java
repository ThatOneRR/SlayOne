package me.thatonerr.slayone.screens;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.ui.UIAbilityMeter;
import me.thatonerr.slayone.ui.UIButton;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.info.Character;
import me.thatonerr.slayone.managers.ScreenManager;
import me.thatonerr.slayone.values.CharacterState;
import me.thatonerr.slayone.values.GameState;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterScreen extends UIScreen {

    private Character character;
    private CharacterState sectionState;
    private UIButton closeButton;
    private CharacterSectionScreen sectionScreen;
    private Map<CharacterState, UIScreen> contentScreens = new HashMap<>();
    private List<UIScreen> abilityPages = new ArrayList<>();
    private List<UIAbilityMeter> abilityMeters = new ArrayList<>();

    public CharacterScreen(ScreenManager screenManager, Game game, float xStart, float yStart) {
        super(screenManager, game, xStart, yStart, game.getWidth(), game.getHeight());
        character = Character.getInstance();

        closeButton = new UIButton("x", GameFont.MAIN_OPTION_TEXT, Color.LIGHT_GRAY, Color.GRAY, null, null, this, game, 10, 5, 30, 30);
        sectionScreen = new CharacterSectionScreen(this, screenManager, game);

        abilityPages.add(new AbilityPage1(abilityMeters, screenManager, game));

        contentScreens.put(CharacterState.PASSIVE, new PassiveScreen(screenManager, game));
        contentScreens.put(CharacterState.ABILITY_1, new AbilityScreen1(abilityPages, abilityMeters,  screenManager, game));
        contentScreens.put(CharacterState.ABILITY_2, new AbilityScreen2(abilityPages, abilityMeters, screenManager, game));
        contentScreens.put(CharacterState.SKINS, new SkinScreen(screenManager, game));
    }

    @Override
    public void refresh() {
        setSectionState(CharacterState.PASSIVE);
        sectionScreen.refresh();
    }

    @Override
    public void onComponentClick(String name) {
        switch (name.toLowerCase()) {
            case "x":
                screenManager.setGameState(GameState.MAIN_MENU);
                break;
        }
    }

    @Override
    public void tick() {
        closeButton.tick();
        sectionScreen.tick();
        contentScreens.get(sectionState).tick();
    }

    @Override
    public void render(Graphics2D graphics) {
        closeButton.render(graphics);
        sectionScreen.render(graphics);
        contentScreens.get(sectionState).render(graphics);
    }

    public void setSectionState(CharacterState sectionState) {
        this.sectionState = sectionState;
        contentScreens.get(sectionState).refresh();
    }

}
