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
import java.util.Optional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ClydeTest {

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
    void ClydeIsNotShy() {
        List<String> layout = Lists.newArrayList(
            "########################################",
            "# P                                   C#",
            "########################################"
        );
        Level level = gmp.parseMap(layout);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.EAST);
        assertThat(clyde.nextAiMove()).contains(Direction.WEST);
    }

    @Test
    void ClydeIsShy() {
        List<String> layout = Lists.newArrayList(
            "########################################",
            "# P       C                            #",
            "########################################"
        );
        Level level = gmp.parseMap(layout);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        level.registerPlayer(pacman);
        pacman.setDirection(Direction.EAST);
        assertThat(clyde.nextAiMove()).contains(Direction.EAST);
    }


    @Test
    void ClydeHasNoSquare() {
        List<String> layout = Lists.newArrayList(
            "########################################",
            "# P                                    #",
            "########################################"
        );
        Level level = gmp.parseMap(layout);
        assertThat(Navigation.findUnitInBoard(Clyde.class, level.getBoard())).isEqualTo(null);
    }

    @Test
    void PacmanHasNoSquare() {
        List<String> layout = Lists.newArrayList(
            "########################################",
            "# P       C                            #",
            "########################################"
        );
        Level level = gmp.parseMap(layout);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertThat(clyde.nextAiMove()).isEqualTo(Optional.empty());
    }

}
