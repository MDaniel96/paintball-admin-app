import {Game} from '../../model/Game';
import {DELETE_GAME, EDIT_GAME, GameAction, SET_GAMES, SET_SELECTED_GAME} from '../actions/GameActions';

export type GameState = {
    games: Game[];
    selectedGame: Game;
}

const gameReducer = (state: GameState = {games: [], selectedGame: new Game()}, action: GameAction): GameState => {
    switch (action.type) {
        case SET_GAMES:
            return {
                games: action.games,
                selectedGame: state.selectedGame
            };
        case SET_SELECTED_GAME:
        case EDIT_GAME:
            return {
                games: state.games,
                selectedGame: action.games[0]
            };
        case DELETE_GAME:
            return {
                games: state.games.filter(game => game.id !== action.deletedGameId),
                selectedGame: state.selectedGame
            };
    }

    return {
        games: state.games,
        selectedGame: state.selectedGame
    };
}

export default gameReducer;
