import {GameFilter} from '../../model/util/GameFilter';
import {GameService} from '../../service/GameService';
import {Game} from '../../model/Game';
import {EditGameRequest} from '../../model/util/EditGameRequest';

export type GameAction = {
    type: string;
    games: Game[];
    deletedGameId: number;
}

export const SET_GAMES = 'SET_GAMES';
export const SET_SELECTED_GAME = 'SET_SELECTED_GAME';
export const EDIT_GAME = 'EDIT_GAME';
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

export const getGameAction = (id: number) => {
    return async (dispatch: GameDispatch) => {
        const game = await GameService.getGame(id);
        dispatch({
            type: SET_SELECTED_GAME,
            games: [game],
            deletedGameId: -1
        });
    }
}

export const editGameAction = (id: number, editGameRequest: EditGameRequest) => {
    return async (dispatch: GameDispatch) => {
        const game = await GameService.editGame(id, editGameRequest);
        dispatch({
            type: EDIT_GAME,
            games: [game],
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
