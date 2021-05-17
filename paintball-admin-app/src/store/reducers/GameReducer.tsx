import {Game} from '../../model/Game';
import {DELETE_GAME, GameAction, SET_GAMES} from '../actions/GameActions';

export type GameState = {
    games: Game[];
}

const gameReducer = (state: GameState = {games: []}, action: GameAction): GameState => {
    switch (action.type) {
        case SET_GAMES:
            return {
                games: action.games
            };
        case DELETE_GAME:
            return {
                games: state.games.filter(game => game.id !== action.deletedGameId)
            }
    }

    return {
        games: state.games
    };
}

export default gameReducer;
