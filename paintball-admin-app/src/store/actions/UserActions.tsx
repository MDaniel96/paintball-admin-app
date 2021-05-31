import {User} from '../../model/User';
import {UserService} from '../../service/UserService';
import {RegisterUserRequest} from '../../model/util/RegisterUserRequest';

export type UserAction = {
    type: string;
    user: User;
}

export const SET_LOGGED_IN_USER = 'SET_LOGGED_IN_USER';
export const REGISTER_USER = 'REGISTER_USER';

type UserDispatch = (args: UserAction) => UserAction

export const getLoggedInUserAction = () => {
    return async (dispatch: UserDispatch) => {
        const user = await UserService.getLoggedInUser();
        dispatch({
            type: SET_LOGGED_IN_USER,
            user: user
        });
    }
}

export const registerUserAction = (registerUserRequest: RegisterUserRequest) => {
    return async (dispatch: UserDispatch) => {
        const data = await UserService.registerUser(registerUserRequest);
        dispatch({
            type: REGISTER_USER,
            user: data
        });
    }
}
