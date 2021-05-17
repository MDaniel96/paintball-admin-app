import {GameFilter} from '../../model/util/GameFilter';
import {GameService} from '../../service/GameService';
import {Game} from '../../model/Game';

export type GameAction = {
    type: string;
    games: Game[];
    deletedGameId: number;
}

export const SET_GAMES = 'SET_GAMES';
export const DELETE_GAME = 'DELETE_GAME';

type GameDispatch = (args: GameAction) => GameAction

export const getGamesAction = (gameFilter: GameFilter) => {
    return async (dispatch: GameDispatch) => {
        const games = await GameService.getGames(gameFilter);
        dispatch({
            type: SET_GAMES,
            games: games,
            deletedGameId: -1
        });
    }
}

export const deleteGameAction = (gameId: number) => {
    return async (dispatch: GameDispatch) => {
        await GameService.deleteGame(gameId);
        dispatch({
            type: DELETE_GAME,
            games: [],
            deletedGameId: gameId
        });
    }
}
