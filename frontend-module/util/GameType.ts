export enum GameType {
  LEAGUE_OF_LEGENDS = 'LEAGUE_OF_LEGENDS',
  DOTA2 = 'DOTA2'
}

export const gameTypeToPrintableName = (type: GameType) => {
  switch (type) {
    case GameType.LEAGUE_OF_LEGENDS:
      return 'League Of Legends';
    case GameType.DOTA2:
      return 'Dota 2';
  }
};
