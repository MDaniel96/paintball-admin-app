export class GameFilter {
    state: string | undefined;
    name: string | undefined;
    type: string | undefined;
    date: Date | undefined;

    constructor({state, name, type, date}:
                    { state?: string, name?: string, type?: string, date?: Date } = {}) {
        this.state = state;
        this.name = name;
        this.type = type;
        this.date = date;
    }
}
