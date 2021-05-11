import Config from '../constants/Config';

const AUTH_API = `${Config.base_url}/auth`;

export class AuthService {

    static async login(username: string, password: string): Promise<any> {
        try {
            const formData = new FormData();
            formData.append("username", username);
            formData.append("password", password);

            let url = `${AUTH_API}/login`;
            let response = await fetch(url, {
                method: 'POST',
                headers: {'Content-Type': 'multipart/form-data'},
                body: formData
            });
            return await response.json();
        } catch (error) {
            console.error('Login', error);
            return null;
        }
    }
}
