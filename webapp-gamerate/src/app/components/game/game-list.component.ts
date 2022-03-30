import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from "rxjs";
import { Game } from "./game.model";
import { GameService } from "./game.service";

@Component({
    selector: 'game-list',
    templateUrl: './game-list.component.html',
    styleUrls: ['./game-list.component.css'],
    providers: [ GameService ] 
})

export class GameListComponent implements OnInit {
    sub: Subscription
    title: string = 'Game List';
    games: Game[] = [];
    gameForm: FormGroup;
    errorMessage: string;

    constructor(private fb: FormBuilder, private gameService: GameService) { }

    ngOnInit(): void {
        this.gameForm = this.fb.group({
            id: null,
            title: ['', Validators.required],
            comment: '',
            rating: [null, Validators.required]
        });
        this.getAllGames();
    }

    ngOnDestroy(): void {
        this.sub.unsubscribe();
    }

    getAllGames() {
        this.gameService.getAllGames().subscribe(games => {
            this.games = games;
        });
    }

    submit() {
        console.log('Submit was called on game form: ', this.gameForm.value);
        this.reset();
    }

    add(game: Game) {
        console.log(game);
        this.gameService.createGame(game).subscribe(() => {
            console.log("Game created");
            this.getAllGames();
        });
    }

    update(game: Game) {
        this.gameService.updateGame(game).subscribe(() => {
            console.log("Game with id " + game.id + " updated");
            this.getAllGames();
        })
    }

    edit(game: Game) {
        var id = game.id;
        console.log('id to be edited', id);
        this.gameForm.patchValue({
            id: game.id,
            title: game.title,
            comment: game.comment,
            rating: game.rating,
        });
    }

    delete(game: Game) {
        this.gameService.deleteGame(game).subscribe(() => {
            console.log("Game deleted");
            this.getAllGames();
        });
    }

    reset() {
        this.gameForm.reset();
    }
}