import { GameTable } from '@/components/GameTable';
import { GameType } from '@/util/GameType';
import { Space, Typography } from 'antd';

const Games = () => {
  return (
    <>
      <Typography.Title>Spielvergleich</Typography.Title>
      <Space style={{ width: '100%' }} size={'large'}>
        <GameTable game={GameType.LEAGUE_OF_LEGENDS} />
        <GameTable game={GameType.DOTA2} />
      </Space>
    </>
  );
};

export default Games;
