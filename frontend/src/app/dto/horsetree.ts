export interface HorseTree {
  id: number;
  name: string;
  description: string;
  birthDay: string;
  sex: string;
  favoriteSportId: number;
  favoriteSportName: string;
  mother: HorseTree;
  father: HorseTree;
}

export const emptyHorseTree = (): HorseTree => ({
  id: null,
  name: null,
  description: null,
  birthDay: null,
  sex: null,
  favoriteSportId: null,
  favoriteSportName: null,
  mother: null,
  father: null,
});

export const createHorse = <T extends Partial<HorseTree>>(
  initialValues: T
): HorseTree & T => {
  return Object.assign(emptyHorseTree(), initialValues);
};
