package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class InkyTest {

    private GhostMapParser gmp;
    private Player pacman;

    @BeforeEach
    void setUp() {
        //角色显示
        PacManSprites sprites = new PacManSprites();
        //用于构造player
        PlayerFactory playerFactory = new PlayerFactory(sprites);
        //提供LevelFactory
        GhostFactory ghostFactory = new GhostFactory(sprites);
        //游戏场景
        BoardFactory boardfactory = new BoardFactory(sprites);

        DefaultPointCalculator pc = new DefaultPointCalculator();
        LevelFactory levelFatory = new LevelFactory(sprites, ghostFactory, pc);
        gmp = new GhostMapParser( levelFatory, boardfactory, ghostFactory);
        pacman = playerFactory.createPacMan();
    }

    @Test
    void InkyInFront() {
        List<String> layout = Lists.newArrayList(
            "########################################",
            "#                                      #",
            "#           I         P           A    #",
            "#                                      #",
            "########################################"
        );
        Level level = gmp.parseMap(layout);
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.WEST);
        assertThat(inky.nextAiMove()).contains(Direction.WEST);
    }

    @Test
    void InkyBehind() {
        List<String> layout = Lists.newArrayList(
            "########################################",
            "#                                      #",
            "#       I    P                   A     #",
            "#                                      #",
            "########################################"
        );
        Level level = gmp.parseMap(layout);
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.EAST);
        assertThat(inky.nextAiMove()).contains(Direction.EAST);
    }


    @Test
    void InkyHasNoSquare() {
        List<String> layout = Lists.newArrayList(
            "########################################",
            "#                                      #",
            "#                      P               #",
            "# A                                    #",
            "########################################"
        );
        Level level = gmp.parseMap(layout);
        assertThat(Navigation.findUnitInBoard(Inky.class, level.getBoard())).isEqualTo(null);
    }

    @Test
    void PacmanHasNoSquare() {
        List<String> layout = Lists.newArrayList(
            "########################################",
            "#     A                           I    #",
            "#                                      #",
            "#                                      #",
            "########################################"
        );
        Level level = gmp.parseMap(layout);
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assertThat(inky.nextAiMove()).isEqualTo(Optional.empty());
    }


    @Test
    void BlinkyHasNoSquare() {
        List<String> layout = Lists.newArrayList(
            "########################################",
            "#                  P              I    #",
            "#                                      #",
            "#                                      #",
            "########################################"
        );
        Level level = gmp.parseMap(layout);
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assertThat(inky.nextAiMove()).isEqualTo(Optional.empty());
    }
}
