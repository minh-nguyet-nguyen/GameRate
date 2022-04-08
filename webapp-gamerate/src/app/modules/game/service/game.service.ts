import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Game } from "../model/game.model";
import { Observable, throwError } from "rxjs";
import { tap, catchError } from "rxjs/operators";

@Injectable()
export class GameService {
    private gameUrl = 'http://localhost:8081/game/';

    constructor(private http: HttpClient) {}
    
    getAllGames(): Observable<Game[]> {
        return this.http.get<Game[]>(this.gameUrl).pipe(
            tap(data => console.log('All: ', JSON.stringify(data))),
            catchError(this.handleError)
        );
    }

    createGame(game: Game): Observable<Object> {
        return this.http.post(this.gameUrl, game).pipe(
            catchError(this.handleError)
        );
    }

    updateGame(game: Game): Observable<Object> {
        return this.http.put(this.gameUrl + game.id, game).pipe(
            catchError(this.handleError)
        );
    }

    deleteGame(game: Game): Observable<Object> {
        return this.http.delete(this.gameUrl + game.id).pipe(
            catchError(this.handleError)
        );
    }

    private handleError(err: HttpErrorResponse) {
        let errorMessage = '';
        if (err.error instanceof ErrorEvent) {
            errorMessage = `An error occured: ${err.error.message}`;
        }
        else {
            errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
        }
        console.error(errorMessage);
        return throwError(errorMessage);
    }
}