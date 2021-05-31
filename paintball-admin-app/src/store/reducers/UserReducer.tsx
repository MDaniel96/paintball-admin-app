import {User} from '../../model/User';
import {REGISTER_USER, SET_LOGGED_IN_USER, UserAction} from '../actions/UserActions';

export type UserState = {
    loggedInUser: User;
}

const userReducer = (state: UserState = {loggedInUser: new User()}, action: UserAction): UserState => {
    switch (action.type) {
        case SET_LOGGED_IN_USER:
            return {
                loggedInUser: action.user
            };
        case REGISTER_USER: {
            const data: any = action.user;
            if (data.httpStatus === 'BAD_REQUEST') {
                alert(data.message);
            } else {
                alert('Registration successful');
            }
            return {
                loggedInUser: new User()
            };
        }
    }

    return {
        loggedInUser: new User()
    };
}

export default userReducer;
